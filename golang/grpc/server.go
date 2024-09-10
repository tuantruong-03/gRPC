package main

import (
	"fmt"
	"log"
	"net"

	"com.golang.grpc/account"
	"github.com/gin-gonic/gin"
	"google.golang.org/grpc"
)

func main() {
	// Start the gRPC server in a separate goroutine
	go func() {
		if err := createGrpcServer(); err != nil {
			log.Fatalf("Failed to start gRPC server: %v", err)
		}
	}()
	// Start the HTTP server
	router := gin.Default()
	router.GET("/health", func(ctx *gin.Context) {
		ctx.String(200, "health")
	})
	// createGrpcServer()

	if err := router.Run(":8080"); err != nil {
		log.Fatalf("Failed to start HTTP server: %v", err)
	}

}

func createGrpcServer() error {
	lis, err := net.Listen("tcp", ":9090")
	if err != nil {
		return fmt.Errorf("failed to listen: %w", err)
	}
	defer lis.Close()

	grpcServer := grpc.NewServer()
	accountServer := account.Server{}
	account.RegisterAccountServiceServer(grpcServer, &accountServer)
	fmt.Println("gRPC server is running on port :9090")

	if err := grpcServer.Serve(lis); err != nil {
		return fmt.Errorf("failed to serve: %w", err)
	}

	return nil
}
