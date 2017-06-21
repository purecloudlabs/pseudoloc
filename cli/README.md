# pseudoloc-cli

pseudoloc-cli is a very thin [Airline 2](https://github.com/rvesse/airline)-based Command-Line Interface which wraps the Pseudoloc Adapters.

## Build

```
$ mvn clean package
```

## Usage

```
$ java -jar cli/target/pseudoloc-cli-0.1-SNAPSHOT-jar-with-dependencies.jar 
usage: pseudoloc <command> [ <args> ]

Commands are:
    help      Display help information
    android

See 'pseudoloc help <command>' for more information on a specific command.

```

## Example

For this example, we'll start with an input Android strings.xml containing some basic English language strings:
```xml
<?xml version="1.0" encoding="utf-8" ?>
<resources>
    <string name="input_very_short">No</string>
    <string name="input_short">Hello world</string>
    <string name="input_standard">I am the walrus</string>
    <string name="input_long">The quick brown fox jumps over the lazy dog</string>
</resources>
```

Pseudo-localize it with this command:
```
$ java -jar cli/target/pseudoloc-cli-0.1-SNAPSHOT-jar-with-dependencies.jar android generate-strings-xml /path/to/input/strings.xml /path/to/output/strings.xml
```

Now the output strings.xml file contains:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<resources>
    <string name="input_very_short">[Nō]</string>
    <string name="input_short">[‘Hēllō wŏrldя]</string>
    <string name="input_standard">[‘İI åm thē wâlrũśя]</string>
    <string name="input_long">[‘İ球Thē qũĩćk brōwń fŏx jūmpś ővęr thě låźy dȭĝ________яش]</string>
</resources>
```
