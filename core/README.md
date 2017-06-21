# pseudoloc-core

pseudoloc-core contains the `Generator` and its supporting classes, which perform the pseudo-localization transformation of language strings.

## Usage

```kotlin
val generator = Generator()
val input = "Genesys is Trusted by Over 10,000 Companies Globally"
val expected = "[‘İ球Gēńęśyş ĩś Trũştěd by Ovēr 10,000 Cōmpåņĭęś Glŏbâlly___________яش]"

val actual = generator.generate(input)

assertEquals(expected, actual)
```