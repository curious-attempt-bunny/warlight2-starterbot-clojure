# Create upload zip

    ./package.sh

This creates `bot.zip` ready for uploading to warlight2.

# Run tests

    lein spec

# Create new tests

1. Capture the log output from a game your bot has played. Something like http://theaigames.com/competitions/warlight-ai-challenge-2/games/HEX_KEY_FOR_THE_GAME_PLAYED/dump.
2. Trim the output down to end at the point where you want to correct the bot's behaviour (e.g. right after `go place_armies` on round 1, or right after `go attack/transfer` on round 5, etc.).
3. Add the validity conditions you require.
4. Save the file as "spec/NameOfTheAspectYouAreTesting.txt".
5. Run the tests to verify that it fails.

## Validity condition format

See [an example](https://github.com/curious-attempt-bunny/warlight2-starterbot-clojure/blob/master/spec/Attacks.txt#L47) to see where these validity conditions go.

### Exact output

Use `# Valid: THE_EXACT_OUTPUT_YOU_EXPECT`.

### Fragment of output

Use `# Valid: [THE_FRAGMENT_OF_OUTPUT_YOU_EXPECT]

### Output you don't want

Use `# Valid: !THE_EXACT_OUTPUT_YOU_DONT_WANT` or `# Valid: ![THE_FRAGMENT_OF_OUTPUT_YOU_DONT_WANT]`.

## Code structure

* bot.clj - the run loop (you shouldn't need to change this).
* brain.clj - the logic for picking starting regions, placing armies, and attacking or transfering.
* handlers.clj - the logic to parse incoming information and requests for action (you should rarely need to touch this).
* launcher.clj - an ugly hack to make the code work as a single script with the warlight2 engine.


