# School Registration System
Design and implement simple school registration system
- Assuming you already have a list of students
- Assuming you already have a list of courses
- A student can register to multiple courses
- A course can have multiple students enrolled in it.
- A course has 50 students maximum
- A student can register to 5 course maximum

Provide the following REST API:
- Create students CRUD
- Create courses CRUD
- Create API for students to register to courses
- Create abilities for user to view all relationships between students and courses
+ Filter all students with a specific course
+ Filter all courses for a specific student
+ Filter all courses without any students
+ Filter all students without any courses

# How to setup project

    git clone https://github.com/esrgrlk/school-registration-system.git


    mvn clean install


    docker build -t school-registration-system .


    docker-compose up


# Endpoints and payloads

## Get All Courses

### Request

`GET /courses`

    http://localhost:8080/courses

### Response

    [{"id":1,"name":"Mathematics"},{"id":2,"name":"Physics"},{"id":3,"name":"English"},{"id":4,"name":"History"},{"id":5,"name":"Economics"},{"id":6,"name":"Biology"},{"id":7,"name":"Chemistry"},{"id":8,"name":"Spanish"},{"id":9,"name":"French"},{"id":10,"name":"Music"}]

## Get a Course By Id

### Request

`GET /courses/{id}`

    http://localhost:8080/courses/1

### Response

    {
        "id": 1,
        "name": "Mathematics"
    }

## Create a Course

### Request

`POST /courses`

    http://localhost:8080/courses

#### Request Body
    {
        "name": "Geometry"
    }

## Delete a Course

### Request

`DELETE /courses/{id}`

    http://localhost:8080/courses/11

## Update a Course

### Request

`PUT /courses`

    http://localhost:8080/courses

#### Request Body
    {
        "id": 1,
        "name": "Geometry"
    }

## Get Courses Without Students

### Request

`GET /courses/withoutStudents`

    http://localhost:8080/courses/withoutStudents

### Response

    [
        {
            "id": 11,
            "name": "Algorithms",
            "studentDTOList": []
        }
    ]

## Query Courses

Query courses with filters

Query Operator: EQUAL | NOT_EQUAL | LIKE | IN

Field Type: STRING | INTEGER | BOOLEAN | LONG | DOUBLE | CHAR

Sort Direction: ASC | DESC

### Request

`POST /courses/query`

    http://localhost:8080/courses/query

#### Request Body

    {
        "filters": [
            {
                "field": "name",     
                "operator": "EQUAL",
                "fieldType": "STRING",
                "value": "History"
            }
        ],
        "sortFields": [
            {
                "field": "name",
                "direction": "ASC"
            }
        ],
        "page": 0,
        "size": 50
    }

### Response

    {
        "content": [
            {
                "id": 4,
                "name": "History",
                "studentDTOList": [
                    {
                        "id": 1004,
                        "name": "Tom",
                        "surname": "Williams"
                    }
                ]
            }
        ],
        "pageable": {
            "sort": {
                "empty": true,
                "sorted": false,
                "unsorted": true
            },
            "offset": 0,
            "pageNumber": 0,
            "pageSize": 50,
            "paged": true,
            "unpaged": false
        },
        "last": true,
        "totalPages": 1,
        "totalElements": 1,
        "size": 50,
        "number": 0,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "first": true,
        "numberOfElements": 1,
        "empty": false
    }

## Query Courses For a Specific Student

### Request

`POST /courses/query`

    http://localhost:8080/courses/query

#### Request Body
    {
        "filters": [
            {
                "field": "students.id",     
                "operator": "EQUAL",
                "fieldType": "INTEGER",
                "value": 1002
            }
        ],
        "sortFields": [
            {
                "field": "name",
                "direction": "ASC"
            }
        ],
        "page": 0,
        "size": 50
    }

### Response
    {
        "content": [
            {
                "id": 3,
                "name": "English",
                "studentDTOList": [
                    {
                        "id": 1001,
                        "name": "John",
                        "surname": "Smith"
                    },
                    {
                        "id": 1003,
                        "name": "Rachel",
                        "surname": "Brown"
                    },
                    {
                        "id": 1002,
                        "name": "Jane",
                        "surname": "Taylor"
                    }
                ]
            },
            {
                "id": 2,
                "name": "Physics",
                "studentDTOList": [
                    {
                        "id": 1001,
                        "name": "John",
                        "surname": "Smith"
                    },
                    {
                        "id": 1002,
                        "name": "Jane",
                        "surname": "Taylor"
                    }
                ]
            }
        ],
        "pageable": {
            "sort": {
                "empty": true,
                "sorted": false,
                "unsorted": true
            },
            "offset": 0,
            "pageNumber": 0,
            "pageSize": 50,
            "paged": true,
            "unpaged": false
        },
        "last": true,
        "totalPages": 1,
        "totalElements": 2,
        "size": 50,
        "number": 0,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "first": true,
        "numberOfElements": 2,
        "empty": false
    }

## Get All Students

### Request

`GET /students`

    http://localhost:8080/students

### Response

    [{"id":1001,"name":"John","surname":"Smith"},{"id":1002,"name":"Jane","surname":"Taylor"},{"id":1003,"name":"Rachel","surname":"Brown"},{"id":1004,"name":"Tom","surname":"Williams"},{"id":1005,"name":"Daisy","surname":"Johnson"},{"id":1006,"name":"Sophie","surname":"Turner"},{"id":1007,"name":"Daniel","surname":"Green"},{"id":1008,"name":"Robert","surname":"Harris"},{"id":1009,"name":"James","surname":"Walker"},{"id":1010,"name":"Mary","surname":"White"}]

## Get a Student By Id

### Request

`GET /students/{id}`

    http://localhost:8080/students/1001

### Response

    {
        "id": 1001,
        "name": "John",
        "surname": "Smith"
    }

## Create a Student

### Request

`POST /students`

    http://localhost:8080/students

#### Request Body
        {
            "name": "Esra",
            "surname": "Yurt"
        }

## Delete a Student

### Request

`DELETE /students/{id}`

    http://localhost:8080/students/1011

## Update a Student

### Request

`PUT /students`

    http://localhost:8080/students

#### Request Body
    {
        "id": 1001,
        "name": "Harry",
        "surname": "Brown"
    }

## Get Students Without Courses

### Request

`GET /students/withoutCourses`

    http://localhost:8080/students/withoutCourses

### Response

    [
        {
            "id": 1012,
            "name": "Esra",
            "surname": "Yurt",
            "courseDTOList": []
        }
    ]

## Register to Courses

### Request

`POST /students/registerToCourses`

    http://localhost:8080/students/registerToCourses

#### Request Body

    {
        "id": 1001,
        "courseIdList": [8, 9]
    }

### Response

    {
        "id": 1001,
        "name": "Harry",
        "surname": "Brown",
        "courseDTOList": [
            {
                "id": 2,
                "name": "Physics"
            },
            {
                "id": 3,
                "name": "English"
            },
            {
                "id": 9,
                "name": "French"
            },
            {
                "id": 8,
                "name": "Spanish"
            },
            {
                "id": 1,
                "name": "Geometry"
            }
        ]
    }

## Query Students

Query students with filters

Query Operator: EQUAL | NOT_EQUAL | LIKE | IN

Field Type: STRING | INTEGER | BOOLEAN | LONG | DOUBLE | CHAR

Sort Direction: ASC | DESC

### Request

`POST /students/query`

    http://localhost:8080/students/query

#### Request Body

    {
        "filters": [
            {
                "field": "id",     
                "operator": "EQUAL",
                "fieldType": "INTEGER",
                "value": 1001
            }
        ],
        "sortFields": [
            {
                "field": "name",
                "direction": "ASC"
            }
        ],
        "page": 0,
        "size": 50
    }

### Response

    {
        "content": [
            {
                "id": 1001,
                "name": "Harry",
                "surname": "Brown",
                "courseDTOList": [
                    {
                        "id": 3,
                        "name": "English"
                    },
                    {
                        "id": 1,
                        "name": "Geometry"
                    },
                    {
                        "id": 2,
                        "name": "Physics"
                    }
                ]
            }
        ],
        "pageable": {
            "sort": {
                "empty": true,
                "sorted": false,
                "unsorted": true
            },
            "offset": 0,
            "pageNumber": 0,
            "pageSize": 50,
            "paged": true,
            "unpaged": false
        },
        "last": true,
        "totalPages": 1,
        "totalElements": 1,
        "size": 50,
        "number": 0,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "first": true,
        "numberOfElements": 1,
        "empty": false
    }

## Query Students For a Specific Course

### Request

`POST /students/query`

    http://localhost:8080/students/query

#### Request Body
    {
        "filters": [
            {
                "field": "courses.id",     
                "operator": "EQUAL",
                "fieldType": "INTEGER",
                "value": 2
            }
        ],
        "sortFields": [
            {
                "field": "name",
                "direction": "ASC"
            }
        ],
        "page": 0,
        "size": 50
    }

### Response
    {
        "content": [
            {
                "id": 1001,
                "name": "Harry",
                "surname": "Brown",
                "courseDTOList": [
                    {
                        "id": 3,
                        "name": "English"
                    },
                    {
                        "id": 2,
                        "name": "Physics"
                    },
                    {
                        "id": 1,
                        "name": "Geometry"
                    }
                ]
            },
            {
                "id": 1002,
                "name": "Jane",
                "surname": "Taylor",
                "courseDTOList": [
                    {
                        "id": 3,
                        "name": "English"
                    },
                    {
                        "id": 2,
                        "name": "Physics"
                    }
                ]
            }
        ],
        "pageable": {
            "sort": {
                "empty": true,
                "sorted": false,
                "unsorted": true
            },
            "offset": 0,
            "pageNumber": 0,
            "pageSize": 50,
            "paged": true,
            "unpaged": false
        },
        "last": true,
        "totalPages": 1,
        "totalElements": 2,
        "size": 50,
        "number": 0,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "first": true,
        "numberOfElements": 2,
        "empty": false
    }