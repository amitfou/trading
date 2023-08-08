export class Order {
  "id": number;
  "ticker": string;
  "quantity": number;
  "price": number;
  "status": string;
  "type": string;

  constructor() {
    this.quantity = 0;
    this.price = 0.0;
  }
}

export async function getOrders(): Promise<Array<Order>> {
  const orders = await fetch("http://localhost:8080/orders")
    .then((response) => response.json())
    .then(function (response) {
      return response;
    })
    .catch(function (error) {
      console.log(error);
    });

  return orders;
}

export async function getExecutedOrders(): Promise<Array<Order>> {
  const orders = await fetch("http://localhost:8080/orders/executed")
    .then((response) => response.json())
    .then(function (response) {
      console.log(response);
      return response;
    })
    .catch(function (error) {
      console.log(error);
      return error;
    });

  return orders;
}

export async function submitOrder(order: Order): Promise<any> {
  const requestOptions = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(order),
  };

  return await fetch("http://localhost:8080/orders", requestOptions)
    .then((response) => response)
    .catch(function (error) {
      return error;
    });
}
