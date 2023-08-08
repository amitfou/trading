import * as React from "react";
import Toolbar from "@mui/material/Toolbar";
import Button from "@mui/material/Button";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import { Box, Menu, MenuItem } from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import { Link, useNavigate } from "react-router-dom";

interface HeaderProps {
  sections: ReadonlyArray<{
    title: string;
    url: string;
  }>;
  title: string;
}

export default function Header(props: HeaderProps) {
  const navigate = useNavigate();
  const { sections, title } = props;
  const [anchorElNav, setAnchorElNav] = React.useState<null | HTMLElement>(
    null
  );

  const handleOpenNavMenu = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorElNav(event.currentTarget);
  };

  const handleCloseNavMenu = (url: string) => {
    setAnchorElNav(null);
    navigate(url);
  };

  return (
    <React.Fragment>
      <Toolbar
        sx={{
          borderBottom: 1,
          borderColor: "divider",
          bgcolor: "#283D84",
          color: "white",
          maxHeight: "10px",
        }}
      >
        <Typography
          variant="subtitle1"
          color="inherit"
          align="left"
          noWrap
          sx={{ flex: 1, ml: 2 }}
        >
          {title}
        </Typography>
      </Toolbar>
      <Box sx={{ display: { xs: "flex", md: "none" } }}>
        <IconButton
          size="large"
          aria-label="account of current user"
          aria-controls="menu-appbar"
          aria-haspopup="true"
          onClick={handleOpenNavMenu}
          color="inherit"
        >
          <MenuIcon />
        </IconButton>
        <Menu
          id="menu-appbar"
          anchorEl={anchorElNav}
          anchorOrigin={{
            vertical: "bottom",
            horizontal: "left",
          }}
          keepMounted
          transformOrigin={{
            vertical: "top",
            horizontal: "left",
          }}
          open={Boolean(anchorElNav)}
          onClose={handleCloseNavMenu}
          sx={{
            display: { xs: "block", md: "none" },
          }}
        >
          {sections.map((page) => (
            <MenuItem
              key={page.title}
              onClick={(event) => handleCloseNavMenu(page.url)}
              href={page.url}
            >
              <Typography textAlign="center">{page.title}</Typography>
            </MenuItem>
          ))}
        </Menu>
      </Box>
      <Toolbar
        component="nav"
        variant="dense"
        sx={{
          justifyContent: "right",
          overflowX: "auto",
          display: { xs: "none", md: "flex", minHeight: "80px" },
        }}
      >
        <Typography
          variant="h5"
          color="inherit"
          align="left"
          noWrap
          sx={{ flex: 1, ml: 2, color: "#5BA344" }}
        ></Typography>
        {sections.map((section) => (
          <Button key={section.title} href={section.url}>
            {section.title}
          </Button>
        ))}
      </Toolbar>
    </React.Fragment>
  );
}
