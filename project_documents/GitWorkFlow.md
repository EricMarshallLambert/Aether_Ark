##GitFlow
"Gitflow is a legacy Git workflow"

https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow

##TrunkFlow
"Trunk-based development is a version control management practice where developers merge small, frequent updates to a core “trunk” or main branch.
Since it streamlines merging and integration phases, it helps achieve CI/CD and increases software delivery and organizational performance."

https://www.atlassian.com/continuous-delivery/continuous-integration/trunk-based-development

##AetherArch Git Workflow
````
Preserving Observiblity
Preserving Traceabiliy
Preserving History
Preserving Sanity
Preserving for Tomorrows Engineers (best practice)
	
I propose our workflow is a variation of Trunkflow.
	
1. Make new "feature" branch that will be a small chunk 
    (some exceptions may apply*). Before working on the branch make sure 
    to push to remote so we know the branch exists.
2. Make meaningfull commits (1-3)
3. Pull from remote to update your local repo
4. Rebase main into YOUR feature branch thus rewriting the git 
    history of your branch to one commit. Use a List format to 
    explain the changes using the Pull Request Template 
    (This is eazier todo in the IDE git integration).
    Use a ticketing number system for searchability
    (This is optional. Just an Idea).
5. Make sure the project builds after taking care of any merge conflicts.
6. When your task(s) are complete make a Pull Request On GitHub.
    DO NOT MERGE YOUR FEATURE BRANCH INTO MAIN LOCALY.
7. Assign reviewers to your PR. 
8. Review and Merge to main.
````
	
	
*The Documentation branch will be a long-lasting feature branch. Merge to main when project done.	
	
To reduce merge conflicts and blockers we will not work on the same files at the same time.	
This all will increase Asynchronous efficiency.

##Naming Conventions

    1. Folders
        -Stick to project 4 naming conventions and heirarchy
    2. Files
        -Stick to project 4 naming conventions. Seems to be best practice.
        -Test folder seperate or tests in same package as class?


#Pull Request Template

I thought I would make a pull request template to make things easier. I believe the PR will automatically import your commit msg into the body of the request so this might not be necessary.

Sticking to this makes commits have a higher searchable content and common structure.

https://docs.github.com/en/communities/using-templates-to-encourage-useful-issues-and-pull-requests/creating-a-pull-request-template-for-your-repository

	Feature:
	Refactor:
	Bugfix:
	Chore:
	Test:
	