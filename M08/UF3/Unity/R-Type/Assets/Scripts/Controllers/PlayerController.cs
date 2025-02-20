using UnityEngine;

public class PlayerController : MonoBehaviour
{
    [Range(5f,10f)]
    public float speed = 1f;
    Vector3 targetPosition;
    public GameObject prefab;
    public Transform firePoint;

    void Awake() {
        Debug.Log("Awake");
    }

    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        Debug.Log("Start");
    }

    // Update is called once per frame
    void Update()
    {
        if(Input.GetMouseButton(0)) {
            Debug.Log("You have pressed left-click");
            targetPosition = Camera.main.ScreenToWorldPoint(Input.mousePosition);
            targetPosition.z = 0;
        }
        else {
            targetPosition = transform.position + new Vector3(Input.GetAxis("Horizontal"),Input.GetAxis("Vertical"), 0);
        }
        
        transform.position = Vector3.Lerp(transform.position, targetPosition, speed * Time.deltaTime);

        if(Input.GetMouseButtonDown(0)) {
            Instantiate(prefab, firePoint.position, Quaternion.identity);
        }
        /* -----
            Input.mousePosition();
            Camera.main.ScreenToWorldPoint();
        */
    }
}
