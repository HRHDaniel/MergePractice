# Merge Example

#### Problem overview
Initial backlog had us create a webservice to retrieve a customer list or a single customer by last name.  The first checkin completed this request.

After the initial demo, our product owner requested 3 changes.
1. An option to sort names alphabetically by last name, then by first name.
2. When retrieving by last name, optionally ignore case during the search.
3. Retrieve a count of all customers.

2 developers completed the above in parallel.

The ignore case and count features were completed on branch "FB\_IgnoreCase", which was then submitted and pulled to the dev branch.  (a branch "original\_dev" was created to show where dev was prior to this merge)

The sorting was completed on branch "FB\_Sort", but now needs to merge the changes on dev before submitting a pull request.  This merge will result in a merge conflict.  

After reviewing the state of the code at each branch, checkout the "FB_Sort" branch and attempt to resolve the merge conflict.  Test cases are presented (and will not conflict during the merge) which can be used to validate that the merge was completed successfully without losing any of the new functionality.
