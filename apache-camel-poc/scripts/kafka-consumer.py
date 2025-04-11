from kafka import KafkaConsumer

TOPIC_NAME = "my-topic"
BROKER = "localhost:9092"

consumer = KafkaConsumer(
    TOPIC_NAME,
    bootstrap_servers=[BROKER],
    auto_offset_reset='earliest',
    enable_auto_commit=True,
    group_id='my-consumer-group'
)

print(f"Consuming messages from topic: {TOPIC_NAME}")

for message in consumer:
    print(f"Received: {message.value.decode('utf-8')}")
