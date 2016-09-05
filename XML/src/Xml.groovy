def inFile = new File('/home/visual/dev/projects/intellij/Groovy/XML/src/in.xml')
def slurper = new XmlSlurper()

def document = slurper.parse(inFile)
println document

println ''

println 'Parent gender : ' + document.Parent.@gender
println 'Parent name   : ' + document.Parent.Name

def prefix = 'Child name    : '
for(child in document.Parent.Child) {
    println "$prefix" + child.Name + ' ' + child.YearOfBirth
}

def markupBuilder = new groovy.xml.StreamingMarkupBuilder()
def xml = markupBuilder.bind {
    mkp.xmlDeclaration(version: "1.0", encoding:"UTF-8")
    Document {
        mkp.comment('Element Parent')
        Parent(gender:'Male') {
            Name('Zlatomir')

            Child {
                Name('Dijana')
                YearOfBirth(1982)
            }

            Child {
                Name('Dejan')
                YearOfBirth(1985)
            }
        }
    }
}

def outFile = new File('/home/visual/dev/projects/intellij/Groovy/XML/src/out.xml')
outFile.write(xml.toString())