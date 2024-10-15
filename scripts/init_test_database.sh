#!/bin/bash
cat <<EOF > test/api/database.json
{
  "employees": [
    {
      "id": 1,
      "name": "Strong Steven",
      "city": "Szeged",
      "salary": 395
    }
  ]
}
EOF

