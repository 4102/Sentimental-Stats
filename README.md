# Sentimental Stats
This is the Scala team's term project for ITCS 4102/5102. The Sentimental Stats project will collect and analyze subjective impressions of a sports teams performance from news outlets and social media, and attempt to generate useful predictions of a team's future performance as evaluated through both those sentimental stats and publicly available 'objective' stats, such as margin-of-victory.

## Informal Description/ Roadmap
As envisioned now, the Sentimental Stats program will have several modules:

1. **A Web Crawler** that finds and collects news and social media posts written about a given sports team's season. This may use Google's [Custom Search API](https://developers.google.com/custom-search/json-api/v1/overview), Twitter's REST/Streaming APIs, Reddit's API, and a simple single-domain web crawler for exploring previously-discovered 3rd-party sites.

2. **A Scraper/Parser** to identify, categorize and normalize content the Crawler discovers. Extra information captured about each opinion might be the author, source (tweet, comment, news article), site, and date written.

3. **A Sentiment Analysis Module** ([Sentiment Analysis on Wikipedia](https://en.wikipedia.org/wiki/Sentiment_analysis) which will derive measurable statistics about subjective opinions. For example, it might try to discern predictions about a particular result (team will lose/win), degree of confidence, general sentiment towards the team (like/dislike, fan/not-fan), or conditions that aren't usually captured by stats (if a game is a rivalry, if a team is a particularly bad matchup, if a player is sick/injured or otherwise expected to perform poorly).

4. **Statistical Analysis Module** which will, at a minimum, compare the 'subjective' stats collected by the sentiment analysis with publicly-available 'objective' statistics (such as win/loss, margin of victory, etc.), compute more advanced statistics from the basic ones (efficiency, usage, etc.), and look for correlations.

5. **Presentation Module** If we have time, it would be nice if the program could also present interesting results (correlations, overlaying graphs, bayesian analysis of simple hypotheses) after it has computed them. If we have even more time, publishing and automatically updating the results would be pretty cool.

## How to Use Git[Hub] if you haven't before

1. If you haven't used Git of GitHub before, install the [GitHub Desktop Client](https://desktop.github.com/).

2. Next, open it up and go to `File > Clone Repository` and choose `Sentimental Stats`. This will copy the project to your local computer. Now you can edit it directly with the editor/IDE of your choice.

3. You can save your changes with the `Commit and Sync to "branch"` button that appears at the bottom of the Desktop Client. **Do not commit directly to the master branch**. In fact, I may protect it so that you can't do that at all. Use `File > New Branch` to make a branch for yourself; name it whatever you want, and make your commits to that branch. This helps *a lot* if multiple people make conflicting changes to the same file.

* Check ["Issues"](https://github.com/4102/Sentimental-Stats/issues) on the GitHub site to see what everyone is working on. Please comment on an issue if something is blocking you, if you need help, or to make suggestions/call out problems in someone else's work.

* If you want the rest of the group to see the changes you have commited and synced, go to `Repository > Create Pull Request`. This creates a place for discussing your changes; we hopefully won't need it very much.

* If you want to add your changes to the `master branch`, go to `Branch > Merge into master`. If someone else has made conflicting changes Git can usually resolve them on its own; otherwise do you best and we'll figure it out together if necessary.

## Who works on what

For now, we're making an issue in GitHub for each module that needs to be worked on. Ideally, 
* each issue will correspond to an important piece of the project,
* each issue will have one person primarily responsible for it, 
* and each issue will explicitly demonstrate a few Scala features (and call them out in comments/documentation!)
* but everyone can work on everything if they have the time.
