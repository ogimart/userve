# μServe

Clojure Microservice Template

## Description

Quite often we need to quickly create a few microservices with REST API or with a couple of endpoints for service health checks. They all usually have the same or similar structure and common dependencies. μServe is a small microservice Leiningen template built around Stuart Sierra's [component](https://github.com/stuartsierra/component), that help us avoid writing the same boilerplate code. μServe aims to be a micro template for microservices, rather than full-blown web or system framework. This template utilizes Walmart Lab's [schematic](https://github.com/walmartlabs/schematic) and [dyn-edb](https://github.com/walmartlabs/dyn-edn) libraries for configuring components and environment in edn files. 

## Components

- Component
- Schematic
- Dyn-edn
- Immutant HTTP & Websocket server
- Bidi url routing
- Redis client
- H2 database driver
- HikariCP connection pool
- Logback logging
- Dockerfile

# Usage

``` shell
lein new userve service-name
```

## Directory structure

Files and directories description:
- `main.clj` : main entry point and component system creation from config file (default is in resources dir.)
- `components/` : system components
- `domain/` : data model, access to database
- `services/` : abstract service interfaces (protocols and multi methods)
- `impl/` : implementation of service interfaces
- `routes/` : ring handler and http routes
- `monitor/` : monitoring components and health checks
- `util/` : utilites and ring middleware
- `resources/` : components and logging config

## TODO v0.2.0

- [ ] DB and Redis connect retries handling
- [ ] Dockerfile
- [ ] nREPL component
- [ ] Tests

## License

Copyright © 2019 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
