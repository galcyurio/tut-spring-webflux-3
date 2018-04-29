````bash
$ mongo
> use todo
switched to db todo
> db.createCollection("todoItem")
{ "ok" : 1 }
> show collections
system.indexes
todoItem
> db.todoItem.insert([{"done" : false, "subject" : "ubuntu linux", "deadline" : ISODate("2017-12-31T00:00:00Z") }])
> db.todoItem.insert([{"done" : false, "subject" : "windows", "deadline" : ISODate("2017-11-31T00:00:00Z") }])
````