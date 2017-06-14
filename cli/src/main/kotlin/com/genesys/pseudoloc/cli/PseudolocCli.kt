package com.genesys.pseudoloc.cli

import com.genesys.pseudoloc.cli.android.GenerateStringsXml
import com.github.rvesse.airline.annotations.Cli
import com.github.rvesse.airline.help.Help

@Cli(
        name = "pseudoloc",
        description = "Command-line interface for generating pseudolocalization files",
        commands = arrayOf(
                GenerateStringsXml::class,
                Help::class
        ),
        defaultCommand = Help::class
)
class PseudolocCli {

    companion object Launcher {
        @JvmStatic
        fun main(vararg args: String) {
            val cli = com.github.rvesse.airline.Cli<Runnable>(PseudolocCli::class.java)
            val cmd = cli.parse(*args)
            cmd.run()
        }
    }
}