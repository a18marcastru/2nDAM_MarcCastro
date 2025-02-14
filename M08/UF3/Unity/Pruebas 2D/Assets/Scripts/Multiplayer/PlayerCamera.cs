using UnityEngine;
using Mirror;

public class PlayerCamera : NetworkBehaviour
{
    public GameObject playerCamera;

    public override void OnStartLocalPlayer()
    {
        if (playerCamera != null)
        {
            playerCamera.SetActive(true); // Activa la cámara solo para el jugador local
        }
    }

    void Start()
    {
        if (!isLocalPlayer && playerCamera != null)
        {
            playerCamera.SetActive(false); // Desactiva la cámara de otros jugadores
        }
    }
}
