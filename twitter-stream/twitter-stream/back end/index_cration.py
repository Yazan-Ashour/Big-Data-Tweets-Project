from elasticsearch import Elasticsearch

es = Elasticsearch("http://localhost:9200")

index_name = "tweets"
mapping = {
    "mappings": {
        "properties": {
            "created_at": {"type": "date", "format": "E MMM dd HH:mm:ss Z yyyy"},
            "text": {"type": "text"},
            "sentiment": {"type": "keyword"},
            "hashtags": {"type": "keyword"},
            "location": {"type": "geo_point"}
        }
    }
}
if not es.indices.exists(index=index_name):
    response = es.indices.create(index=index_name, body=mapping)
    print(f"Index '{index_name}' created: {response}")
else:
    print(f"Index '{index_name}' already exists.")