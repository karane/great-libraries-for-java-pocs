from kafka import KafkaProducer
import random
import time

producer = KafkaProducer(bootstrap_servers='localhost:9092')

messages = [
    "Hello from Python!",
    "Kafka is awesome!",
    "Apache Camel rocks!",
    "Random Message 1",
    "Random Message 2",
    "Another Event",
    "Streaming data is fun",
]

while True:
    msg = random.choice(messages)
    producer.send('my-topic', value=msg.encode('utf-8'))
    print(f"Sent: {msg}")
    time.sleep(1)  # wait for 2 seconds
