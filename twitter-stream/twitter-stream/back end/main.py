from fastapi import FastAPI,Query
from fastapi.middleware.cors import CORSMiddleware
from elasticsearch import Elasticsearch

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:3000"],  
    allow_credentials=True,
    allow_methods=["*"],  
    allow_headers=["*"], 
)

es = Elasticsearch("http://localhost:9200") 

@app.get("/search_tweets")
async def search_tweets(keyword: str = Query(..., description="Keyword to search in tweets")):
    try:
        response = es.search(
            index="tweets",
            body={
                "query": {
                    "match": {
                        "text": keyword
                    }
                },
                "size": 1000  
            }
        )

        tweets = [
            {
                "created_at": hit["_source"].get("created_at"),
                "text": hit["_source"].get("text"),
                "hashtags": hit["_source"].get("hashtags"),
                "sentiment": hit["_source"].get("sentiment"),
                "location": hit["_source"].get("location"),
            }
            for hit in response["hits"]["hits"]
        ]
        return {"tweets": tweets}

    except Exception as e:
        return {"error": str(e)}
