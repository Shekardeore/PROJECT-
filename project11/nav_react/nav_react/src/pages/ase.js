function ase() {
  if (sessionStorage.getItem("user") === null) {
    window.location = "/";
  } else {
    // if (sessionStorage.getItem("user").userRole === "normal") {
    if (JSON.parse(sessionStorage.user).userRole === "User") {
      window.location = "/user/viewcomplaints";
    }
  }
}
export default ase;
