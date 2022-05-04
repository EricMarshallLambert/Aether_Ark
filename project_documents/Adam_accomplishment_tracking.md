# Adam Schraedel - [Aether Ark] Accomplishment Tracking

## Background

It's a great habit to record accomplishments and progress throughout your SDE
career. It's useful to reflect on what you've worked on in the past and comes in
handy during performance reviews and promotion cycles.


## Week 1

**Goals:** Finalize project design, get set up with Trello.

**Activity:** I made the original UML diagrams, we filled out design documentation, mapped out endpoints/class structures

**Important Docs, Commits, or Code Reviews**: Design_document, UML diagrams

**Things learned:** Designing in detail is a lot of work, and there is a lot that goes into it. 
Sequence diagrams, documentation, endpoints, class architecture, etc.
But it turns out (later) that it's worth it.

## Week 2

**Goals:** Build out basic CelestialBody structure, build most of the CelBody endpoints in the backend, 
CelestialBody DynamoDB table.

**Activity:** Wrote the classes for CelestialBody architecture, many of the activity endpoints as well. Wrote out DynamoDB table.

**Important Docs, Commits, or Code Reviews**: Java classes: ModelConverter, CelestialBody, CelestialBodyModel, CelestialBodyDao.
Other Activity classes.

**Things learned:** The actual writing process wasn't too hard, it just took time to copy templates and flesh them out. 
I expected that. But it would have been even easier if I had made specific detailed design plans that outlined what I needed to write and where.
Then the planning would be all done and the writing would just be the Execute part.

## Week 3

**Goals:** Complete CelestialBody backend, build out API.

**Activity:** Completed java for CelestialBody activities, integrated API on API Gateway, integrated DynamoDB CelBody table, 
setup Postman for integrated testing. Also major debug of SolarSystemUpdate on Lambda, and refactored CelestialBody object
to include a SolarSystem Map of Ids to Strings(names) instead of a List of SolarSystem Objects. Lots of work getting API 
to integrate correctly.

**Important Docs, Commits, or Code Reviews**: Rest of CelestialBody Activities, API Gateway, Postman workspace,
commits: Lambda bomb fixes, Dynamodb bomb fixes, Actual Lambda Fix, UpdateSolar activity, Body map issues

**Things learned:** If I had thought through the design to begin with, I would not have put a list of Objects into 
the CelestialBody table, because they were an endless loop of objects that were saving each other. That caused some big problems,
so I needed to refactor that whole attribute throughout the project. Luckily it was rarely used, since the front end didn't
really exist yet. Also, the API actually can be very in-depth and detailed. Just to get it working required a lot of work and tweaking
and learning, but to get it actually accurate and fully functional for other people to use it, would take a lot more time 
and even more detailed work.

## Week 4

**Goals:** Finish up documentation, get presentation ready.

**Activity:** Prepared for presentation, planned out Postman calls for demonstration.

**Important Docs, Commits, or Code Reviews**: Presentation, rubric, accomplishment_tracking

**Things learned:** The presentation is just as important as the actual work. I get annoyed at presentations and things
like that, but if you don't present it well, no one can tell that you did well on the actual project. They didn't spend 
the time on it like you did.
