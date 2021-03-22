
# **Team Endpoints**
___

1. Team
    * Get All Teams
    * Get Team by Team Name
    * Create Team
    * Update Team
    * Delete Team
___

### **GET Path: '/team'**
GET All teams (returns all non deleted teams)
</br>
Returns Json:
```json
[
    {
        "teamName": "Sales",
        "teamDescription": "Sell products to Clients.",
        "parentCompany": {
            "companyName": "Dunder Mifflin",
            "companyDescription": "Paper and paper accessories."
        },
        "teamMembers": [
            {
                "userName": "Loki",
                "firstName": "Jim",
                "lastName": "Halpert",
                "password": "Pranks",
                "userRole": {
                    "roleTitle": "Member"
                }
            },
            {
                "userName": "Darth Shrewt",
                "firstName": "Dwight",
                "lastName": "Schrute",
                "password": "Beets",
                "userRole": {
                    "roleTitle": "Member"
                }
            }
        ],
        "projects": [
            {
                "name": "Sell Paper",
                "description": "Without watermarks."
            }
        ],
        "isDeleted": false
    },...
]
```

### **GET Path: '/team/{teamName}'**
GET team by team name
</br>
Returns Json:
```json
{
    "teamName": "Sales",
    "teamDescription": "Sell products to Clients.",
    "parentCompany": {
        "companyName": "Dunder Mifflin",
        "companyDescription": "Paper and paper accessories."
    },
    "teamMembers": [
        {
            "userName": "Loki",
            "firstName": "Jim",
            "lastName": "Halpert",
            "password": "Pranks",
            "userRole": {
                "roleTitle": "Member"
            }
        },
        {
            "userName": "Darth Shrewt",
            "firstName": "Dwight",
            "lastName": "Schrute",
            "password": "Beets",
            "userRole": {
                "roleTitle": "Member"
            }
        }
    ],
    "projects": [
        {
            "name": "Sell Paper",
            "description": "Without watermarks."
        }
    ],
    "isDeleted": false
}
```

### **POST Path: '/team/create'**
POST team
</br>
Request Json:
```
{
    "teamName":"Party Planning Committee",
    "teamDescription":"Plans Parties."
}
```
Returns Json:
```json
{
    "teamName": "Party Planning Committee",
    "teamDescription": "Plans Parties.",
    "parentCompany": null,
    "teamMembers": [],
    "projects": [],
    "isDeleted": false
}
```

### **PATCH Path: '/team/update/{teamName}'**
PATCH team (update team info)
</br>
Request Json:
```
{
    "credentials":{
        "userName":"Michael Scarn",
        "password": "Friendship"

    },
    "changes":{
        "teamName":"Sales Team Fun Parade",
        "teamDescription":"Selling Smiles"
    }

}
```
Returns Json:
```json
{
    "teamName": "Sales Team Fun Parade",
    "teamDescription": "Selling Smiles",
    "parentCompany": {
        "companyName": "Dunder Mifflin",
        "companyDescription": "Paper and paper accessories."
    },
    "teamMembers": [
        {
            "userName": "Loki",
            "firstName": "Jim",
            "lastName": "Halpert",
            "password": "Pranks",
            "userRole": {
                "roleTitle": "Member"
            }
        },
        {
            "userName": "Darth Shrewt",
            "firstName": "Dwight",
            "lastName": "Schrute",
            "password": "Beets",
            "userRole": {
                "roleTitle": "Member"
            }
        }
    ],
    "projects": [
        {
            "name": "Sell Paper",
            "description": "Without watermarks."
        }
    ],
    "isDeleted": false
}
```

### **DELETE Path: '/team/delete/{teamName}'**
DELETE team 
</br>
Request Json:
```
{
    "userName":"Michael Scarn",
    "password": "Friendship"
}
```
Returns Json:
```json
{
    "teamName": "Sales",
    "teamDescription": "Sell products to Clients.",
    "parentCompany": {
        "companyName": "Dunder Mifflin",
        "companyDescription": "Paper and paper accessories."
    },
    "teamMembers": [
        {
            "userName": "Loki",
            "firstName": "Jim",
            "lastName": "Halpert",
            "password": "Pranks",
            "userRole": {
                "roleTitle": "Member"
            }
        },
        {
            "userName": "Darth Shrewt",
            "firstName": "Dwight",
            "lastName": "Schrute",
            "password": "Beets",
            "userRole": {
                "roleTitle": "Member"
            }
        }
    ],
    "projects": [
        {
            "name": "Sell Paper",
            "description": "Without watermarks."
        }
    ],
    "isDeleted": true
}
```