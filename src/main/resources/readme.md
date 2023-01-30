**Resources rules:**

Resource should be named *Controller <br/>
Resource should not define transactional boundaries <br/>
Resource should be annotated with RestController annotation <br/>

**Services rules:**

Service should be transactional <br/>
Service should use @Service stereotype <br/>
Service should not return entity <br/>

Only service or repository can access EntityManager <br/>

**Model rules**

Only model can be annotated as Entity

**Architecture layer's rules:**

access:<br/>

Resource->Service->Repository
