#!/bin/sh

cat src/bot.clj src/brain.clj src/handlers.clj src/launcher.clj > packaged.clj
zip bot.zip packaged.clj
rm packaged.clj