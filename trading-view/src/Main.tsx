import * as React from "react";

import {
  RouterProvider,
  createRoutesFromElements,
  createBrowserRouter,
  Route,
} from "react-router-dom";
import { RootLayout } from "./RootLayout";
import Home from "./pages/Home";
import Portfolio, { loader } from "./pages/Portfolio";
import ListOrder, {
  loader as loadOrder,
  loadExecutedOrder,
} from "./pages/ListOrder";

export default function Main() {
  const router = createBrowserRouter(
    createRoutesFromElements(
      <Route path="/">
        <Route path="/" element={<RootLayout />}>
          <Route index path="/" element={<Home />}></Route>
          <Route
            path="/portfolio"
            loader={loader}
            element={<Portfolio />}
          ></Route>
          <Route
            path="/listOrders"
            loader={loadOrder}
            element={<ListOrder />}
          ></Route>
          <Route
            path="/listExecutedOrders"
            loader={loadExecutedOrder}
            element={<ListOrder />}
          ></Route>
        </Route>
      </Route>
    )
  );

  return <RouterProvider router={router} />;
}
