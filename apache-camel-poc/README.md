# Apache Camel POC


## Prerequisites
- Java 17+ 
- Maven 3.x 
- Docker & Docker Compose


## Run Kafka
```bash
docker-compose up --build -d
```
Check the logs, if Kafka is running properly.
```bash
docker-compose logs -f
```

In another terminal, create kafka producer
```bash
cd scripts
python3 -m venv venv
source venv/bin/activate
pip install -r requirements.txt

python kafka-producer.py
```


## Run pipelines
- Java Lambda Router
- Health Router
- Kafka Router

```bash
mvn compile exec:java -Dexec.mainClass=org.karane.MyApplication
```

## Run CamelBasic pipeline
```bash
mvn compile exec:java -Dexec.mainClass=org.karane.CamelBacic
```
## Run Camel console pipeline
```bash
mvn compile exec:java -Dexec.mainClass=org.karane.CamelConsole
```

Type any text, and see it being printed uppercase.



