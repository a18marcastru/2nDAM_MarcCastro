using UnityEngine;
using UnityEngine.AI;

public class Enemy : MonoBehaviour
{
    [Header("Configuración")]
    [SerializeField] private Transform player;  // Referencia al jugador
    [SerializeField] private float speed = 3f;  // Velocidad de persecución

    private NavMeshAgent agent;

    void Start()
    {
        agent = GetComponent<NavMeshAgent>();
        agent.updateRotation = false; // Desactiva la rotación automática
        agent.updateUpAxis = false;   // Evita cambios en el eje Z (útil en 2D)
        agent.speed = speed; // Asigna la velocidad al NavMeshAgent
    }

    void Update()
    {
        ChasePlayer();
    }

    void ChasePlayer()
    {
        if (player != null)
        {
            agent.SetDestination(player.position);
        }
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        if (other.CompareTag("Player"))
        {
            Debug.Log("Atacar");
        }
    }
}