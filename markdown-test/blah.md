Hmmm, thinking more about how these should be structured
20/12/2013
code, markdown

The amount of metadata that each post has is starting to grow a bit, perhaps it'd be good to find someway
of making the metadata optional.  At the moment I have title, date and tags - but if this
continues to grow then I'm going to have painful migrations each time I think of a new bit of metadata
that I want to add.  Maybe all the data could be in one line as a clojure map?  Would be quite nice to keep the clojure away
from the markdown.

Perhaps there could be a special escape character for each line that has some metadata on it.

E.g. $$TITLE, $$DATE etc.. or something like that - a special metadata symbol

```Just out of interest, will triple backticks create a code block, a la github markdown?```
