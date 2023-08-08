import * as React from "react";
import { Order, getExecutedOrders, getOrders } from "../services/orderService";

import {
  Box,
  Chip,
  Grid,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";
import { useLoaderData } from "react-router-dom";

export default function ListOrder() {
  const orders = useLoaderData() as Array<Order>;

  return (
    <Paper variant="elevation" sx={{ bgcolor: "#F8FAFB" }}>
      <Grid container justifyContent="center">
        <Grid item md={8} xs={12}>
          <Box
            sx={{
              position: "relative",
              p: { xs: 3, md: 3 },
              pt: 0,
              pb: 0,
            }}
          >
            {" "}
            <TableContainer
              component={Paper}
              sx={{ maxHeight: { md: 500, sx: 1 } }}
            >
              <Table aria-label="simple table">
                <TableHead>
                  <TableRow>
                    <TableCell>Holding</TableCell>
                    <TableCell align="right">Average Price</TableCell>
                    <TableCell align="right">Quantity </TableCell>
                    <TableCell align="right">Type </TableCell>
                    <TableCell align="right">Status </TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {orders?.map((entry) => (
                    <TableRow
                      key={entry.id}
                      sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                    >
                      <TableCell component="th" scope="row">
                        <Box>
                          <Chip
                            label={entry.ticker}
                            variant="outlined"
                            size="small"
                          />
                        </Box>
                      </TableCell>
                      <TableCell align="right">{entry.price}</TableCell>
                      <TableCell align="right">{entry.quantity}</TableCell>
                      <TableCell align="right">{entry.type}</TableCell>
                      <TableCell align="right">{entry.status}</TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </Box>
        </Grid>
      </Grid>
    </Paper>
  );
}

export async function loader() {
  const orders = await getOrders();
  return orders;
}

export async function loadExecutedOrder() {
  const orders = await getExecutedOrders();
  return orders;
}
