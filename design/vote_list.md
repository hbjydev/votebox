# Vote List

The Vote List view allows the end user (Voter) to specify which vote they wish
to interact with.


## Initial Load

![PlantUML Diagram](https://www.plantuml.com/plantuml/png/RP0n3i8m34NtdC9iC7213gW8HYQa8bPJ35Xo4vIubU3qc10a2ep9Vx7_IvqKe_QTk8bUg2DeAVP2w5sJAC9y0SqUAX1E2aDGIixEThiANcCGeMWfUrP6uJjDP-uZCMvdRxPq3Oarn9knPzAbY75VYR5hL3nWeXS5A59MleE_0pEI2_wC7FKS5SjUHIMBLh1vAeOJOAiyKDbSap_Qvpm0)

As seen in the diagram above, the process flow for this view is that a user
loads it, and then the FXML controller requests that the VoteRepository
retrieves a list of all votes, and returns them as objects back.

From there, the controller can render their names to the ListView component on
the scene, which a user can then pick from.


## Selection

![PlantUML Diagram](https://www.plantuml.com/plantuml/png/7Ov13eCm30JlViNs1byWX_AL51OHoSHLxBHzVe7RIZFIxEoHM_H3vH4x3s9XwyfSvGOYKmaU3hMyejGmQF7u57wJJDtPu5GkuRYy958s3DS4VwVepn75CLGR4Jkh01DRimRhaGJmiBUmhcKxKtVabLKju0ihaznyFeNhuXy0)

When the user can view the list of votes, they select one to proceed with
creating a ballot on. This process is simple, and is outlined in the diagram
above.
