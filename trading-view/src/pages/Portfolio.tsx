import * as React from "react";
import { Holding, getHoldings } from "../services/portfolioService";
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

export default function Portfolio() {
  const holdings = useLoaderData() as Array<Holding>;

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
                  </TableRow>
                </TableHead>
                <TableBody>
                  {holdings?.map((entry) => (
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
                      <TableCell align="right">{entry.averagePrice}</TableCell>
                      <TableCell align="right">{entry.quantity}</TableCell>
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
  const holdings = await getHoldings();
  return holdings;
}
