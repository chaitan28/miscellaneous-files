run:
	docker run -d --name nodeimage1 -p 8082:8082 node-naive 

# 918MB
build-naive:
	docker build \
	-f $(CURDIR)/Dockerfile.naive \
	-t node-naive \
	.

# 142MB
build-sl:
	docker build \
	-f $(CURDIR)/Dockerfile.slm \
	-t node-slim \
	.

# 89.3MB
build-alpine:
	docker build \
	 --no-cache  \
	-f $(CURDIR)/Dockerfile.alpine \
	-t node-alpine \
	.
# docker run --rm <docker-image> whoami
user-check:
	docker run --rm node-alpine whoami  
