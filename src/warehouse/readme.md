[![back to root readme](../../back-button.png)](./../../readme.md)
# User Module 

## Api 
- [x] warehouse:build
- [x] warehouse:store-item
- [x] warehouse:update-item
- [x] warehouse:delete-item

|   Command	|  Data 	|  Action 	|  Information 	|
|---	|---	|---	|---	|	
| warehouse:build  	|  storageSize 	|   setUp()	| will setup everything   	|  	
| warehouse:get-item  	|  Item ID	|   getItem()	| returns a item   	|  	
| warehouse:store-item  	|  jsonialized Item 	|   store()	| stores a item, will fire success event   	|  	
| warehouse:store-item=success  	|  storage ID 	|   -	| -   	|  	
| warehouse:store-item=full_storage  	|  "Storage is full" 	|  -	| -   	|  	
| warehouse:update-item  	|  ItemId@data as json 	|  TBA	| updated ein item   	|  	
| warehouse:delete-item  	|  ItemId  	|  TBA	| löscht ein item   	|  	
