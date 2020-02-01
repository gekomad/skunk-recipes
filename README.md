# Skunk recipes
  
<table>      
<td align="left">  
<a href="https://tpolecat.github.io/doobie/docs/01-Introduction.html">      
    <img src="https://www.iconsdb.com/icons/preview/red/fire-xxl.png" width="90">
</a>  
</td>      
</table>

 [here](https://github.com/gekomad/doobie-recipes) Doobie recipes
    


### Run test with docker
```
docker run -d --name skunk_recipies -p5436:5432 -e POSTGRES_USER=jimmy -e POSTGRES_DB=world -e POSTGRES_PASSWORD=banana tpolecat/skunk-world
sbt test
docker rm -f skunk_recipies

```

- Selecting

    [Select count](https://github.com/gekomad/skunk-recipes/blob/master/src/test/scala/selecting/Count.scala)
    
    [Join](https://github.com/gekomad/skunk-recipes/blob/master/src/test/scala/selecting/Join.scala)
    
    [MappingRows](https://github.com/gekomad/skunk-recipes/blob/master/src/test/scala/selecting/MappingRows.scala)
    
    [NestedClass](https://github.com/gekomad/skunk-recipes/blob/master/src/test/scala/selecting/NestedClass.scala)
    
    [NestedClassMap](https://github.com/gekomad/skunk-recipes/blob/master/src/test/scala/selecting/NestedClassMap.scala)
    
    [RowMappings](https://github.com/gekomad/skunk-recipes/blob/master/src/test/scala/selecting/RowMappings.scala)
    
    [SelectMultipleColumns](https://github.com/gekomad/skunk-recipes/blob/master/src/test/scala/selecting/SelectMultipleColumns.scala)
    
    [SelectOneColumn](https://github.com/gekomad/skunk-recipes/blob/master/src/test/scala/selecting/SelectOneColumn.scala)
    
- Parameterized queries

    [Bigger than](https://github.com/gekomad/skunk-recipes/blob/master/src/test/scala/parameterizedQueries/BiggerThan.scala)
     
    IN clauses
     
    [Parameters](https://github.com/gekomad/skunk-recipes/blob/master/src/test/scala/parameterizedQueries/Parameters.scala)
      
- DDL

    Batch
    
    Insert and Read key
    
    Insert and Read Person class
    
    Insert Read and Update
    
    SQLArrays

- Transactions

    Transaction

- Enum

    Enum
    
- CSV

    Select with type
    
    Itto CSV
    
    Load CSV in table
    
    Spool CSV

    Spool paramterized CSV
    
- Logging

    Logging
    
- Error handling

    ErrorHandling

