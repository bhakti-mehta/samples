This is a very basic sample which shows Pagination with JAX-RS
For simplicity we have an inmemory data for the coffee items
In real life JPA should be used to fetch data from a DB and implement pagination

Download Glassfish 4.0

Start Glassfish
cd glassfish4/glassfish/bin
./asadmin start-domain

deploy pagination.war
./asadmin deploy <folder of sample>target/pagination.war


Send requests like this
curl http://localhost:8080/pagination/v1/coffees/orders?page=1&limit=10

You will see an output like this

[
    {
        "Id": 10,
        "Name": "Latte",
        "Price": 3.09,
        "Type": "Hot",
        "Size": "Medium"
    },
    {
        "Id": 11,
        "Name": "Expresso",
        "Price": 0.78,
        "Type": "Hot",
        "Size": "Extra Large"
    },
    {
        "Id": 12,
        "Name": "Mocha",
        "Price": 3.79,
        "Type": "Brewed",
        "Size": "Extra Small"
    },
    {
        "Id": 13,
        "Name": "Cappuchino",
        "Price": 3.64,
        "Type": "Brewed",
        "Size": "Extra Large"
    },
    {
        "Id": 14,
        "Name": "Mocha",
        "Price": 1.57,
        "Type": "Hot",
        "Size": "Medium"
    },
    {
        "Id": 15,
        "Name": "Americano",
        "Price": 1.64,
        "Type": "Brewed",
        "Size": "Extra Large"
    },
    {
        "Id": 16,
        "Name": "Americano",
        "Price": 4.68,
        "Type": "Brewed",
        "Size": "Small"
    },
    {
        "Id": 17,
        "Name": "Latte",
        "Price": 0.03,
        "Type": "Hot",
        "Size": "Small"
    },
    {
        "Id": 18,
        "Name": "Latte",
        "Price": 4.27,
        "Type": "Hot",
        "Size": "Extra Small"
    },
    {
        "Id": 19,
        "Name": "Latte",
        "Price": 2.2,
        "Type": "Brewed",
        "Size": "Extra Small"
    }
]


