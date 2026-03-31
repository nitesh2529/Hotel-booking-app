import React from "react"
import { Link, useLocation } from "react-router-dom"
import Header from "../common/Header"

const BookingSuccess = () => {
  const location = useLocation()

  if (!location.state) {
    return <p>Loading...</p>
  }

  const { message, error } = location.state

  return (
    <div className="container">
      <Header title="Booking Status" />
      <div className="mt-5">
        {message && (
          <div>
            <h3 className="text-success">Booking Success!</h3>
            <p className="text-success">{message}</p>
          </div>
        )}

        {error && (
          <div>
            <h3 className="text-danger">Error Booking Room!</h3>
            <p className="text-danger">{error}</p>
          </div>
        )}
      </div>
    </div>
  )
}

export default BookingSuccess
