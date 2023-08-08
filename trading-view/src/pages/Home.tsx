import * as React from "react";
import { Order, submitOrder } from "../services/orderService";
import {
  Box,
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  FormControl,
  Grid,
  Paper,
  TextField,
  ToggleButton,
  ToggleButtonGroup,
} from "@mui/material";
import { useNavigate } from "react-router-dom";

export default function Home() {
  const [tradeType, setTradeType] = React.useState("BUY");
  const [order, setOrder] = React.useState(new Order());
  const [open, setOpen] = React.useState(false);
  const [errors, setErrors] = React.useState<any>();

  const navigate = useNavigate();

  const handleType = (event: React.MouseEvent<HTMLElement>, type: string) => {
    if (type) setTradeType(type);
  };

  const handleTicker = (event: React.ChangeEvent<HTMLInputElement>) => {
    setOrder({ ...order, ticker: event.target.value });
  };

  const handlePrice = (event: React.ChangeEvent<HTMLInputElement>) => {
    setOrder({ ...order, price: parseFloat(event.target.value) });
  };

  const handleQty = (event: React.ChangeEvent<HTMLInputElement>) => {
    setOrder({ ...order, quantity: parseInt(event.target.value) });
  };

  const submit = () => {
    submitOrder({ ...order, type: tradeType }).then((response) => {
      console.log(response);
      if (response.status === 200) {
        setErrors(null);
        setOpen(true);
      } else {
        setErrors(response);
        setOpen(true);
      }
    });
  };

  const onClose = () => {
    setOpen(false);
    if (!errors) navigate("/listOrders");
    setErrors(null);
  };

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
            <FormControl fullWidth size="small" margin="dense">
              <TextField
                margin="dense"
                id="explanation"
                label="Stock"
                fullWidth
                onChange={handleTicker}
                inputProps={{ maxLength: 5 }}
              />
            </FormControl>
            <FormControl fullWidth size="small" margin="dense">
              <TextField
                margin="dense"
                id="price"
                label="Price"
                fullWidth
                value={order.price}
                onChange={handlePrice}
              />
            </FormControl>
            <FormControl fullWidth size="small" margin="dense">
              <TextField
                margin="dense"
                id="quantity"
                label="Quantity"
                fullWidth
                value={order.quantity}
                onChange={handleQty}
              />
            </FormControl>
            <FormControl fullWidth size="small" margin="dense">
              <ToggleButtonGroup
                color="primary"
                exclusive
                value={tradeType}
                onChange={handleType}
                aria-label="OrderType"
              >
                <ToggleButton value="BUY">BUY</ToggleButton>
                <ToggleButton value="SELL">SELL</ToggleButton>
              </ToggleButtonGroup>
            </FormControl>
            <FormControl fullWidth size="small" margin="dense">
              <Button variant="contained" onClick={submit}>
                SUBMIT
              </Button>
            </FormControl>
          </Box>
        </Grid>
      </Grid>
      <Dialog
        open={open}
        onClose={onClose}
        aria-labelledby="responsive-dialog-title"
      >
        <DialogTitle id="responsive-dialog-title" sx={{ minWidth: "300px" }}>
          Order Status
        </DialogTitle>
        <DialogContent>
          <DialogContentText>
            {errors
              ? "Order Failed. Please add all details"
              : "Your order was successfull"}
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={onClose} autoFocus>
            OK
          </Button>
        </DialogActions>
      </Dialog>
    </Paper>
  );
}
