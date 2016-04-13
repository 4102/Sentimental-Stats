# Sentimental Stats
This is the Scala team's term project for ITCS 4102/5102. The Sentimental Stats project will collect and analyze subjective impressions of a sports teams performance from news outlets and social media, and attempt to generate useful predictions of a team's future performance as evaluated through both those sentimental stats and publicly available 'objective' stats, such as margin-of-victory.


## Informal Description
As envisioned now, the Sentimental Stats program will have several modules:

1. **A Web Crawler** that finds and collects news and social media posts written about a given sports team's season. This may use Google's [Custom Search API](https://developers.google.com/custom-search/json-api/v1/overview), Twitter's REST/Streaming APIs, Reddit's API, and a simple single-domain web crawler for exploring previously-discovered 3rd-party sites.

2. **A Scraper/Parser** to identify, categorize and normalize content the Crawler discovers. Extra information captured about each opinion might be the author, source (tweet, comment, news article), site, and date written.

3. **A Sentiment Analysis Module** ([Sentiment Analysis on Wikipedia](https://en.wikipedia.org/wiki/Sentiment_analysis) which will derive measurable statistics about subjective opinions. For example, it might try to discern predictions about a particular result (team will lose/win), degree of confidence, general sentiment towards the team (like/dislike, fan/not-fan), or conditions that aren't usually captured by stats (if a game is a rivalry, if a team is a particularly bad matchup, if a player is sick/injured or otherwise expected to perform poorly).

4. **Statistical Analysis Module** which will, at a minimum, compare the 'subjective' stats collected by the sentiment analysis with publicly-available 'objective' statistics (such as win/loss, margin of victory, etc.), compute more advanced statistics from the basic ones (efficiency, usage, etc.), and look for correlations.

5. **Presentation Module** If we have time, it would be nice if the program could also present interesting results (correlations, overlaying graphs, bayesian analysis of simple hypotheses) after it has computed them. If we have even more time, publishing and automatically updating the results would be pretty cool.

## How to Run

1. [Download](https://github.com/4102/Sentimental-Stats/archive/master.zip) the project or clone the repo with `git clone https://github.com/4102/Sentimental-Stats.git`

2. Download and install [sbt](http://www.scala-sbt.org/download.html)
...* This is the only way to build Scala while maintaining sanity.

4. To compile and run, navigate to the repo's root directory from the shell of your choice and enter `sbt run`
...* To run with command-line arguments, wrap them in quotes like so: `sbt "run arg1 arg2`
...* I don't recommend trying to build or run with IntelliJ or Eclipse.'

## How to View/Edit

1. Download and install [IntelliJ](https://www.jetbrains.com/idea/#chooseYourEdition).
2. Select `File > Open...` and navigate to the project if it doesn't detect it on start.
3. Install the Scala plugin when prompted, or [do it manually](https://www.jetbrains.com/help/idea/2016.1/enabling-and-disabling-plugins.html).
4. Add the `lib/` directory to your dependencies/classpath
...* Go to `File > Project Structure > Modules` and select the `Dependencies` tab
...* Select the `+` symbol, choose `jars or directories`,and finally navigate to `lib/`. Phew.
5. The project doesn't reliably compile or run in IntelliJ, so I recommend using sbt instead.

### How to Use GitHub if you haven't before

1. If you haven't used Git of GitHub before, install the [GitHub Desktop Client](https://desktop.github.com/).

2. Next, open it up and go to `File > Clone Repository` and choose `Sentimental Stats`. This will copy the project to your local computer. Now you can edit it directly with the editor/IDE of your choice.

3. You can save your changes with the `Commit and Sync to "branch"` button that appears at the bottom of the Desktop Client. **Do not commit directly to the master branch**. Use `File > New Branch` to make a branch for yourself; name it whatever you want, and make your commits to that branch. This helps *a lot* if multiple people make conflicting changes to the same file.

* Check ["Issues"](https://github.com/4102/Sentimental-Stats/issues) on the GitHub site to see what everyone is working on. Please comment on an issue if something is blocking you, if you need help, or to make suggestions/call out problems in someone else's work.

* If you want the rest of the group to see the changes you have commited and synced, go to `Repository > Create Pull Request`. This creates a place for discussing your changes; we hopefully won't need it very much.

* If you want to add your changes to the `master branch`, go to `Branch > Merge into master`. If someone else has made conflicting changes Git can usually resolve them on its own; otherwise do you best and we'll figure it out together if necessary.

## Dependencies
Sentimental Stats uses:
...* MongoDB and its Scala Driver
...* Twitter4J
...* Standford's NLP library

### Who works on what

For now, we're making an issue in GitHub for each module that needs to be worked on. Ideally, 
* each issue will correspond to an important piece of the project,
* each issue will have one person primarily responsible for it, 
* and each issue will explicitly demonstrate a few Scala features (and call them out in comments/documentation!)
* but anyone can work on anything if they have the time.
