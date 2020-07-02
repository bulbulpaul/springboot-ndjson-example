# SpringBoot ndjson example

Example code of allow ndjson request to Spring MVC.

## request example

use example file of `data/example.ndjson`

```bash
$ cat data/example.ndjson                                                                                                                 
{ "id": "test_1", "name": "Work", "priority": 1 }
{ "id": "test_2", "name": "Todo", "priority": 2 }

$ curl -i -X POST "http://localhost:8080/bulk" -H "accept: */*" -H "Content-Type: application/x-ndjson" --data-binary @data/example.ndjson
HTTP/1.1 201 
Content-Type: application/x-ndjson
Content-Length: 85
Date: Wed, 01 Jul 2020 23:54:48 GMT

{"id":"test_1","name":"Work","priority":1}
{"id":"test_2","name":"Todo","priority":2}
```

## LICENSE
MIT