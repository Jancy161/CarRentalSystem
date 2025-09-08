
import React, { useEffect, useState } from "react";
import { getReservationsByUserId, cancelReservation, updateReservation } from "../services/reservationService";
import { getCarById } from "../services/carService";  


export default function MyReservations() {
  const [reservations, setReservations] = useState([]);
  
  

  // ✅ get user safely from localStorage
  const user = JSON.parse(localStorage.getItem("user"));
  const userId = user?.userId;

  useEffect(() => {
    if (userId) loadReservations();
  }, [userId]);

  const loadReservations = async () => {
    try {
      const reservationsData = await getReservationsByUserId(userId); //data before 
      // Fetch car details 
       // enrich each reservation with car details
/*     const enrichedReservations = await Promise.all(
      reservationsData.map(async (res) => {
        // Check if res.carId exists and is not null or undefined
        if (res.carId) {
          try {
            const carData = await getCarById(res.carId);
            return { ...res, car: carData };
          } catch (err) {
            console.error(`Failed to fetch car details for carId ${res.carId}:`, err);
            return res; // Return the original reservation if car fetch fails
          }
        } else {
          console.warn(`Reservation with ID ${res.reservationId} is missing a carId.`);
          return res; // Return the original reservation if no carId is present
        }
      })
    );

    setReservations(enrichedReservations);
    */
    setReservations(reservationsData);
    } catch (err) {
      console.error("Failed to load reservations:", err);
    }
  };

  const handleCancel = async (reservationId) => {
    try {
      await cancelReservation(reservationId);
       alert("Your reservation is cancelled. Amount will be refunded to your registered bank account/UPI within 24 hrs.");
      loadReservations();
    } catch (err) {
      console.error("Cancel failed:", err);
    }
  };
/*
  const handleModify = async (res) => {
    // Example: extend dropoff date by 1 day
    const updated = { ...res, dropoffDate: "2025-09-10" }; // replace with form input later
    try {
      await updateReservation(updated);
      loadReservations();
    } catch (err) {
      console.error("Update failed:", err);
    }
  };
*/
  return (
    <div className="container py-4">
      <h3>My Reservations</h3>
      <table className="table table-bordered table-hover">
        <thead className="table-dark">
          <tr>
            <th>ID</th>
            
           {/* <th>Car</th>*/}
            <th>Pickup</th>
            <th>Dropoff</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {reservations.map((r) => (
            <tr key={r.reservationId}>
              <td>{r.reservationId}</td>
              
              {/*<td>{r.carId}</td>*/}
              <td>{r.pickupDate}</td>
              <td>{r.dropoffDate}</td>
              <td>{r.status}</td>
              <td>
                {r.status === "ACTIVE" && (
                  <>
                    {/*<button
                      className="btn btn-sm btn-warning me-2"
                      onClick={() => handleModify(r)}
                    >
                      Modify
                    </button> */}
                    <button
                      className="btn btn-sm btn-danger"
                      onClick={() => handleCancel(r.reservationId)}
                    >
                      Cancel
                    </button>
                  </>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
