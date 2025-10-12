#!/bin/bash

BASE_URL="http://localhost:8080"
USERNAME="alice"

echo "🔑 Logging in as $USERNAME..."
TOKEN=$(curl -s -X POST "$BASE_URL/auth/login" \
  -H "Content-Type: application/json" \
  -d "{\"username\":\"$USERNAME\"}" | jq -r '.token')

if [ "$TOKEN" == "null" ] || [ -z "$TOKEN" ]; then
  echo "❌ Failed to retrieve JWT token"
  exit 1
fi

echo "✅ Got token:"
echo "$TOKEN"

echo
echo "📡 Calling protected endpoint with token..."
RESPONSE=$(curl -s "$BASE_URL/auth/hello" \
  -H "Authorization: Bearer $TOKEN")

echo "✅ Response:"
echo "$RESPONSE"
