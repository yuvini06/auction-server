# CO225: Software Construction (Project II: Auction Server)

## Description: 
Implementation of a server which can be used by clients to bid for items in a stock exchange. 
The server can locate a given stock item, update its price. Furthermore the server can track all the changes done to the stock item; how the offers varied with time and who made the offers.

## Specification:
**Server Side:**
- The GUI displays the price of the following Symbols: FB, VRTU, MSFT, GOOGL, YHOO, XLNX, TSLA and TXN. The price of each security will be updated once a successful bid has been made.
- The GUI will also display the prices of securities which have been recently updated.
- The server is able to locate a given stock item, update its price. The updated price along with the symbol of the secuirty and the bidder will be displayed on the GUI.

**Client Side:** 
- Once a client is connected, he/she should expect the first message to be his/her name. (Client authentication is not implemented but the client name will be used for all subsequent bids).
- Once the name is given, the client is expected to provide the Symbol of the security he/she is willing to bid on. If the provided Symbol is found, the server replies back with the current cost of the security or -1 to indicate that the Symbol is invalid.
- Once that is done the clients are not allowed to change neither their names nor the security that they are bidding on.

## Steps:
1. Run Main.java to launch the Auction Server.
2. Connect to the server using nc by specifying the port number as 2000.
  > nc localhost 2000
3. Once connected provide a suitable user-name to identify yourself as a client.
4. Provide the Symbol of the security you are willing to bid on. If the provided Symbol is found, the current cost of the security will be displyed. "-1" is displayed to indicate that the Symbol is invalid (i.e. the provided secuirty is not available in the stock list).
5. If the symbol of the security is found within the list, place a bid for the specific security (the offered bidding price must be of a suitable numerical format and value). Once a bid has been placed successfully, the updated price along with the symbol of the security will be displayed. For a given session you are allowed to bid for only one security.
6. Thereafter you are allowed to place multiple bids for the same item if required. Type 'quit' if you wish to exit.

