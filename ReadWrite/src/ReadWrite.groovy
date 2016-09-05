package com.ga.groovy

println '*** Start ***'

File inputFile = new File('in.txt')

if (!inputFile.exists()) {
    println 'File [' + inputFile + '] dose not exist.'
} else {
    File outputFile = new File('out.txt')
    if (outputFile.exists()) {
        outputFile.delete()
    }
    Writer writer = outputFile.newWriter('UTF-8', true);

    inputFile.eachLine { line ->
        line = line.trim()
        if (line.size() != 0) {

            def template = 'update table M_TABLE_ATTRIBUTES set ANONYMIZATION_ACTION = \'anonymization_action_replace\' where table_name = \'table_name_replace\' and ATTRIBUTE_NAME in (attribute_names_replace);\n'
            def result

            if (line.startsWith('update')) {
                result = template.replace('anonymization_action_replace', 'NOT_TO_BE_ANONYMIZED')
            } else if (line.startsWith('null')) {
                result = template.replace('anonymization_action_replace', 'TO_BE_DELETED')
            } else {
                println 'bad line start'
            }

            def table_name = getTableName(line)
            result = result.replace('table_name_replace', table_name)

            def attributeNames = getAttributeNames(line)
            result = result.replace('attribute_names_replace', attributeNames);

            println result
            writer.write(result + '\n')
        }
    }
    writer.close()
}

def String getTableName(String line) {
    def array = line.split(' ')
    array[1].toUpperCase()
}

def String getAttributeNames(String line) {
    def array = line.split('\\(')
    def attributeNames = array[1].replaceAll('\\)', '').split('\\,')

    StringBuilder result = new StringBuilder();
    for (def item in attributeNames) {
        def a = '\'' + item.trim().toUpperCase() + '\','
        result.append(a)
    }
    result.setLength(result.length() - 1)
    result.toString()
}

println '*** End ***'

