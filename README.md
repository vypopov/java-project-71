### Hexlet tests and linter status:
[![Actions Status](https://github.com/vypopov/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/vypopov/java-project-71/actions)
### Demo
[Watch gendiff stylish demo](https://asciinema.org/a/GYnYPtpfEyqBBoRG)

[Watch gendiff plain demo](https://asciinema.org/a/j33R8WP2lZUjS2kb)

[Watch gendiff json demo](https://asciinema.org/a/fCjzNhI0ihEE62h8)

### Stylish format (default)

```text
$ gendiff file1.json file2.json
{
  - follow: false
    host: hexlet.io
  - proxy: 123.234.53.22
  - timeout: 50
  + timeout: 20
  + verbose: true
}
```

### Plain format

```text
$ gendiff -f plain file1.json file2.json
Property 'follow' was removed
Property 'proxy' was removed
Property 'timeout' was updated. From 50 to 20
Property 'verbose' was added with value: true
```

### JSON format

```json
$ gendiff -f json file1.json file2.json
[{"key":"follow","status":"removed","oldValue":false,"newValue":null},{"key":"host","status":"unchanged","oldValue":"hexlet.io","newValue":null},{"key":"proxy","status":"removed","oldValue":"123.234.53.22","newValue":null},{"key":"timeout","status":"changed","oldValue":50,"newValue":20},{"key":"verbose","status":"added","oldValue":true,"newValue":null}]
```

### JavaCI
[![Java CI](https://github.com/vypopov/java-project-71/actions/workflows/build.yml/badge.svg)](https://github.com/vypopov/java-project-71/actions/workflows/build.yml)
