# windmill-assessment

Used Spring boot and H2 in memory DB for this project.
Also implemented Unit tests for cotroller and service layers.

Cretaed 3 rest endpoints.
  - registering windmill
  ```
OkHttpClient client = new OkHttpClient();

MediaType mediaType = MediaType.parse("application/json");
RequestBody body = RequestBody.create(mediaType, "{\n    \"serialNumber\": \"1234567890qwerty\",\n    \"latitude\": 123.001,\n    \"longitude\": \"123.002\"\n}");
Request request = new Request.Builder()
  .url("http://localhost:8080/windmill/manager/v1/register")
  .post(body)
  .addHeader("Content-Type", "application/json")
  .addHeader("Accept", "*/*")
  .addHeader("Cache-Control", "no-cache")
  .addHeader("Host", "localhost:8080")
  .addHeader("Accept-Encoding", "gzip, deflate")
  .addHeader("Content-Length", "95")
  .addHeader("Connection", "keep-alive")
  .addHeader("cache-control", "no-cache")
  .build();

Response response = client.newCall(request).execute();

```
- posting power metrics

```
OkHttpClient client = new OkHttpClient();

MediaType mediaType = MediaType.parse("application/json");
RequestBody body = RequestBody.create(mediaType, "{\n    \"serialNumber\": \"1234567890qwerti\",\n    \"dateTime\": [\n        2016,\n        1,\n        2,\n        10,\n        35\n    ],\n    \"power\": 10.45\n}");
Request request = new Request.Builder()
  .url("http://localhost:8080/windmill/manager/v1/windmill-metric")
  .post(body)
  .addHeader("Content-Type", "application/json")
  .addHeader("Accept", "*/*")
  .addHeader("Cache-Control", "no-cache")
  .addHeader("Host", "localhost:8080")
  .addHeader("Accept-Encoding", "gzip, deflate")
  .addHeader("Content-Length", "146")
  .addHeader("Connection", "keep-alive")
  .addHeader("cache-control", "no-cache")
  .build();

Response response = client.newCall(request).execute();

```
- endpoint for chart

```
OkHttpClient client = new OkHttpClient();

Request request = new Request.Builder()
  .url("http://localhost:8080/windmill/manager/v1/power-aggregate")
  .get()
  .addHeader("Accept", "*/*")
  .addHeader("Cache-Control", "no-cache")
  .addHeader("Host", "localhost:8080")
  .addHeader("Accept-Encoding", "gzip, deflate")
  .addHeader("Connection", "keep-alive")
  .addHeader("cache-control", "no-cache")
  .build();

Response response = client.newCall(request).execute();

```
