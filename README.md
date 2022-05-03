## InMemRepository

A generic implementation to emulate dynamic repository wrapper over an inmemory KeyValue store
Currently, implemented with [Caffine](https://github.com/ben-manes/caffeine)

### Key Benefits :
1. Immediate bootstrap any key value store as a Repository.
2. Out of the box support for various CRUD operations over a simple key value store.
3. Provides extensible structure over a simple a key-value store , that can easily transform over time into a persistent store.
4. Support for both TTL and non-ttl based in-mem storage.
5. A central monitoring and reporting support for all InMem stores(caches)





