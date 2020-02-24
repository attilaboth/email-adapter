# email-adapter API



## About email adapter project

This is one of the three planned API.
email-adapter API contains *core engine on how to process, store, search and order certain emails*.
These emails should be processed (certain informations from there should be extracted) and usefull
informations should be stored in database. After this phase client API can create GET requests to 
get the specified informations from database and process them further. Also this API shoul be capable
of organizing emails into logical groups based on subject of the email.

So to summarize the core fuctionality of email adapter API here are the main points:
- search in specified email folder for emails and store them in DB
- extract valuable informations from email and store them into separate DB
- organize emails into logical groups based on their subject
- convert date and time from email to unix epoch time format
- be able to send email notifications if email processing fails to complete

Additional functionality will be added later if it is demanded.
***This project is optimized to work with Google mailbox. But it can be easily modified to work also with
Microsoft Outook or any other popular mailbox.***

### About the whole architecture of this project

The whole architecture of this project consists of three APIs:
1. email adapter API
2. secure login API
3. consumer client API

Email adapter contains the **core functionality** as mentioned above. But to securely log in and log out to a certain
mailbox a ***secure login API*** will be developed. This API provides encryption for user login credentials an will check
*if the user has the needed rights to log int mailbox and manipulate with emails.*
And the last API named *consumer client API* is developed to create HTTP request on secure login API and email adapter API.
