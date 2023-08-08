import { useState } from "react";
import { Outlet } from "react-router-dom";
import Header from "./Header";

const sections = [
  { title: "HOME", url: "/" },
  { title: "PORTFOLIO", url: "/portfolio" },
  { title: "ORDERS", url: "/listOrders" },
  { title: "EXECUTED ORDERS", url: "/listExecutedOrders" },
];

export const RootLayout = () => {
  const [menu, setMenu] = useState(sections);

  return (
    <>
      <Header title="Tradeing App" sections={menu}></Header>
      <Outlet />
    </>
  );
};
