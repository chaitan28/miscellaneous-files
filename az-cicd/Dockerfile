FROM mcr.microsoft.com/dotnet/sdk:8.0 AS build
WORKDIR /app
COPY *.sln .
COPY . .
WORKDIR /app/src/Web
RUN dotnet restore
RUN dotnet publish -c Release -o out

FROM mcr.microsoft.com/dotnet/aspnet:8.0 AS runtime
WORKDIR /app
COPY --from=build /app/src/Web/out ./

# Optional: Set this here if not setting it from docker-compose.yml
# ENV ASPNETCORE_ENVIRONMENT Development

ENTRYPOINT ["dotnet", "Web.dll"]


# Your multi-stage Dockerfile is a clever strategy to reduce the size of the final image! Here's how it achieves that:

#     ### Why It Shrinks the Image:
#     1. **Separation of Build and Runtime Stages**:
#        - The first stage (`build`) uses the `mcr.microsoft.com/dotnet/sdk:8.0` image, which is larger because it contains all the tools needed for building and compiling your application.
#        - The second stage (`runtime`) uses the much smaller `mcr.microsoft.com/dotnet/aspnet:8.0` image, containing only the runtime environment necessary to execute the application.
    
#     2. **Copying Only Published Output**:
#        - In the `build` stage, the `dotnet publish` command generates a minimal set of files optimized for deployment (compiled code, dependencies, etc.).
#        - Only these published files are copied into the `runtime` stage (`COPY --from=build`), leaving behind unnecessary build-time artifacts.
    
#     3. **Avoids Including Build Tools**:
#        - Since the final image is based on the lightweight runtime image, all the build tools (like the SDK, compilers, etc.) from the `build` stage are excluded, reducing the overall size.
    
#     4. **Efficient Layer Management**:
#        - Each `COPY` or `RUN` instruction adds a new layer to the image. By separating concerns (build vs. runtime) and optimizing layers, the resulting image is leaner.
   