export class Holding {
  "id": number;
  "ticker": string;
  "quantity": number;
  "averagePrice": number;
}

export async function getHoldings(): Promise<Array<Holding>> {
  const holdings = await fetch("http://localhost:8080/portfolio")
    .then((response) => response.json())
    .then(function (response) {
      return response;
    })
    .catch(function (error) {
      console.log(error);
    });

  return holdings;
}
