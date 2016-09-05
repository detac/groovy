@GrabConfig(systemClassLoader=true)
@Grapes([
    @Grab(
        group   = 'org.postgresql',
        module  = 'postgresql',
        version = '9.4-1206-jdbc42'
    )
])
import groovy.sql.Sql

def sql = Sql.newInstance("jdbc:postgresql://localhost:5432/groovy", "postgres", "postgres", "org.postgresql.Driver")

def groovy = sql.dataSet('my.groovy')

def row = sql.firstRow('select max(id)+1 as next_id from my.groovy')
def next_id = row.next_id

groovy.add(id: next_id, num_value:10.36 * next_id, text_value:'groovy ' + next_id)

sql.eachRow('select * from my.groovy order by id asc') {
    println "id : " + "${it.id}" + " | num_value : " + "${it.num_value}" + " | text_value : " + "${it.text_value}"
}

sql.close()