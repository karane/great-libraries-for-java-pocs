# Apache Calcite - CSV, JSON, Postgres

## How to Run

* Initialize Postgres
```bash
docker compose up
```

* Checking 
```bash
docker exec -it calcite-postgres \
  psql -U appuser -d appdb
```

Inside psql
```sql
SELECT * FROM payments;
```

Or one-liner
```bash
docker exec calcite-postgres \
  psql -U appuser -d appdb -c "SELECT * FROM payments;"
```

And Finally, run:
```bash
mvn exec:java -Dexec.mainClass=org.karane.sqlengine.Engine
```