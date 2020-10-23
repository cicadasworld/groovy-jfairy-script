@Grapes([
   @Grab('com.devskiller:jfairy:0.6.4'),
   @Grab('org.slf4j:slf4j-simple:1.7.25')])

import com.devskiller.jfairy.Fairy
import com.devskiller.jfairy.producer.text.TextProducer

TextProducer text = Fairy.create(Locale.default).textProducer()

println "* Lorem ipsum *"
println text.loremIpsum()

println "* Localised text *"
println text.text()

println "* Latin words *"
println text.latinWord(1)
println text.latinWord()
println text.latinWord(190)

println "* Words *"
println text.word(1)
println text.word()
println text.word(190)

println "* Latin sentences *"
println text.latinSentence(1)
println text.latinSentence()
println text.latinSentence(190)

println "* Sentences *"
println text.sentence()
println text.sentence(3)
println text.sentence(190)

println "* Paragraph *"
println text.paragraph()
println text.paragraph(190)
println text.limitedTo(10).paragraph(190)

println "* Random string *"
println text.randomString(10)
println text.randomString(20)