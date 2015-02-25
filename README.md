# What it can do

It understands the [warlight2 engine](http://theaigames.com/competitions/warlight-ai-challenge-2#) input and provides abstractions for you to describe the actions the bot should take. This starter bot has no brains. It will place all starting armies on one spot and move armies to a random neighbour. It does include a nifty test harness so that you can quickly correct it's behaviour.

Good hunting!

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

## State structure

The central data structure to this bot is the `state`. It's a map that represents the entire game state. Here is what those keys are:

* :max_rounds - e.g. 45
* :starting_armies - e.g. 5
* :time_per_move - e.g. 500
* :last-placement - e.g. [{:region {:id 17, ...}, :armies 5}, ...]
* :regions - a map of region id to region - e.g. {7 {:id 7, :super_region_id 3, :armies 2, :neighbours (13 8 12 6 3), :owner :us}, ... }
* :our_name - e.g. "player1"
* :super_regions - a map of super region id to super region e.g. {5 {:id 5, :reward 1}, ...}
* :their_name - e.g. "player2"
* :their_starting_regions - e.g. (15 4)

### Regions

The keys of a region are:

* :id - e.g. 17
* :super_region_id - e.g. 5
* :armies - e.g. 2
* :neighbours - e.g. (16)
* :owner - e.g. :us
* :wasteland - e.g. true

### Super regions

The keys of a super region are:

* :id - e.g. 5
* :reward - e.g. 1
